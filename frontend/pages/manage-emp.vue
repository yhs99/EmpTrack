<template>
  <div class="container mt-5">
    <h1>Post List</h1>
    <ul>
      <!-- API에서 받아온 포스트들을 리스트로 표시 -->
      <li v-for="post in posts" :key="post.id">
        {{ post.id }}: {{ post.title }}
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

// 반응형 데이터로 posts 배열을 선언
const posts = ref([]);

// 컴포넌트가 마운트되었을 때 API 요청을 실행
onMounted(async () => {
  try {
    const response = await axios.get('https://jsonplaceholder.typicode.com/posts');
    posts.value = response.data; // 받아온 데이터를 posts에 할당
  } catch (error) {
    console.error('API 요청 중 에러 발생:', error);
  }
});
</script>