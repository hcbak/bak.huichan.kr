<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { useToast } from '../utils/toast'
import axios from 'axios'

const { showInfoToast } = useToast();
const router = useRouter();

const items = ref(null);

const fetchMenuItem = async () => {
  try {
    const response = await axios.get(`${import.meta.env.VITE_API_SERVER_URI}/api/v1/menu`);
    items.value = response.data;
  } catch (error) {
    console.error(error);
    if (!items.value) {
      items.value = {
        name: 'Home',
        url: '/'
      };
    }
  }
};

const handleSubItemClick = (subItem) => {
  try {
    router.push(subItem.url);
  } catch {
    showInfoToast('준비 중인 기능입니다.');
  }
};

onMounted( () => {
  fetchMenuItem();
});

</script>

<template>
  <BNavbar toggleable="md">
    <template v-if="items">
      <BNavbarBrand :to="items.url">{{ items.name }}</BNavbarBrand>
      <BNavbarToggle target="nav-collapse" />
      <BCollapse v-if="items.sub" id="nav-collapse" is-nav>
        <BNavbarNav>
          <template v-for="item in items.sub" :key="item">
            <BNavItem v-if="!item.sub" @click="handleSubItemClick(item)">{{ item.name }}</BNavItem>
            <BNavItemDropdown v-if="item.sub" :text="item.name">
              <BDropdownItem
                v-for="subItem in item.sub"
                :key="subItem"
                @click="handleSubItemClick(subItem)"
              >
                {{ subItem.name }}
              </BDropdownItem>
            </BNavItemDropdown>
          </template>
        </BNavbarNav>
        <BNavbarNav class="ms-auto mb-2 mb-lg-0">
          <BNavItem @click="handleSubItemClick()">Sign In</BNavItem>
        </BNavbarNav>
      </BCollapse>
    </template>
  </BNavbar>
</template>

<style scoped>
</style>
