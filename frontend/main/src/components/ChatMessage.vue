<script setup>

const props = defineProps({
  message: {
    type: Object,
    required: true,
  },
});

const formattedTime = (time) => {
  const date = new Date(time);
  const month = date.getMonth() + 1;
  const day = date.getDate();
  const hours = date.getHours().toString().padStart(2, '0');
  const minutes = date.getMinutes().toString().padStart(2, '0');
  return `${month}.${day}. ${hours}:${minutes}`;
};
</script>

<template>
  <div class="chat-message">
    <div class="message-header">
      <span class="message-name">{{ message.name }}</span>
      <span class="message-time">{{ formattedTime(message.time) }}</span>
    </div>
    <div class="message-content">{{ message.message }}</div>
  </div>
</template>

<style scoped>
.chat-message {
  padding: 8px;
  border-radius: 5px;
  display: flex;
  flex-direction: column;
  width: auto;
}

.message-header {
  display: flex;
  justify-content: space-between;
  width: 100%;
  margin-bottom: 4px;
}

.message-name {
  font-size: 0.7rem;
  font-weight: bold;
}

.message-time {
  font-size: 0.7rem;
  color: gray;
}

.message-content {
  font-size: 0.8rem;
  word-break: break-all;
}
</style>
