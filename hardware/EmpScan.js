const { execSync } = require('child_process');

function installDependencies() {
  try {
    console.log('필요 패키지를 확인중입니다.');
    execSync('npm list serialport axios node-notifier || npm install serialport axios node-notifier', { stdio: 'inherit' });
  } catch (error) {
    console.error('패키지 설치중 오류가 발생했습니다 :', error);
    process.exit(1);
  }
}

installDependencies();

const { SerialPort } = require('serialport');
const { ReadlineParser } = require('@serialport/parser-readline');
const axios = require('axios');
const notifier = require('node-notifier');
let port;
try {
  port = new SerialPort({path: 'COM3', baudRate: 9600});
  console.log('RFID 리더기 인식 성공');
} catch (error) {
    console.log(error);
    console.log('RFID 리더기를 인식할 수 없습니다. 3초 후 종료합니다.');
  setTimeout(() => process.exit(1), 3000);
}
const parser = port.pipe(new ReadlineParser({ delimiter: '\r\n' }));

parser.on('data', uuid => {
  sendUUIDToServer(uuid);
});

function sendUUIDToServer(uuid) {
  axios.post('http://emptrack.ddns.net/api/v1/attendance', { uuid })
    .then(response => {
        if(response.status === 200) {
            console.log('이름 : ' + response.data.name);
            console.log(response.data.message);
        }else {
            console.log(response.data.message);
        }
    })
    .catch(error => {
      console.error('출결처리중 오류가 발생했습니다:', error.message);
    });
}

// To keep the program running
process.stdin.resume();