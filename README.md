# 🏢 EmpTrack 

🚀 기존 수기 방식의 출퇴근 기록을 자동화하기 위해 **RFID카드/리더기 와 API 서버를 연동한 근태 관리 시스템**을 개발하였습니다.  

## 🎯 개발 계기  
가게에서 아르바이트생, 직원의 출퇴근 기록을 **종이에 수기로 작성**하는 불편함을 줄이고,  
이를 **디지털화하여 보다 효율적으로 관리**할 수 있도록 직접 개발하게 되었습니다.  

## 🛠 기술 스택  
### Hardware
![image](https://github.com/user-attachments/assets/056d2ce6-769e-40e3-965f-6f833925fe6f)

### BackEnd
![image](https://github.com/user-attachments/assets/141eda4f-4c22-4691-a2ad-704a045e5888)

### FrontEnd
![image](https://github.com/user-attachments/assets/8a70b638-c9cf-473f-bdbf-b57ea282f773)

### Auth
![image](https://github.com/user-attachments/assets/95de8573-15e7-43bd-bca5-9832c2614d94)


## 📌 주요 기능  

### **1️⃣ RFID 카드 태그 감지**
- 아르바이트생, 직원이 RFID 리더기에 카드를 태그시 카드의 UUID를 **BackEnd API 서버로 HTTP 요청**으로 전송
- 출퇴근 시간 자동 기록

### **2️⃣ 웹 에서 직원 정보 조회 및 수정 / 출퇴근 기록 조회 및 문서화**
- 매장 관리자는 **웹에서 직원의 정보를 조회하고 재직 여부를 수정**할 수 있습니다.
- **날짜 별로 출퇴근 기록 조회 및 엑셀 문서화하여 다운로드**가 가능합니다.
- 손쉽게 **예상 급여와 세금을 확인**할 수 있습니다. (예정)

---
