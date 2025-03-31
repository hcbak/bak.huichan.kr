<script setup>
import Header from './components/Header.vue'
import ChatView from './views/ChatView.vue'
</script>

<template>
  <header>
    <Header />
  </header>

  <BContainer fluid="md">
    <RouterView />
  </BContainer>

  <ChatView />
  <BToastOrchestrator />
</template>

<style scoped>
</style>
