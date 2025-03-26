<script setup>
import Header from './components/Header.vue'
</script>

<template>
  <header>
    <Header />
  </header>

  <BContainer fluid="md">
    <RouterView />
  </BContainer>

  <BToastOrchestrator />
</template>

<style scoped>
</style>
