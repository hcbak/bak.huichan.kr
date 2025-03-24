<script setup>
import { ref } from "vue";

const items = ref([
  {
    label: 'Home',
    route: '/'
  },
  {
    label: 'About',
    route: '/about'
  },
  {
    label: 'Projects',
    items: [
      {
        label: 'Components'
      },
      {
        label: 'Blocks'
      },
      {
        label: 'UI Kit'
      }
    ]
  },
  {
    label: 'Contact'
  }
]);

</script>

<template>
  <BNavbar toggleable="md">
    <template v-if="items[0]">
      <BNavbarBrand :to="items[0].route">{{ items[0].label }}</BNavbarBrand>
      <BNavbarToggle target="nav-collapse" />
      <BCollapse v-if="items.length > 1" id="nav-collapse" is-nav>
        <BNavbarNav>
          <template v-for="item in items.slice(1)" :key="item">
            <BNavItem v-if="item.route" :to="item.route">{{ item.label }}</BNavItem>
            <BNavItemDropdown v-if="item.items" :text="item.label">
              <BDropdownItem v-for="subItem in item.items" :key="subItem" :to="subItem.route">{{ subItem.label }}</BDropdownItem>
            </BNavItemDropdown>
          </template>
        </BNavbarNav>
      </BCollapse>
    </template>
  </BNavbar>
  </template>
  
  <style scoped>
  </style>
