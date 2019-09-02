<template>
    <div id="app">
        <nav-header></nav-header>
        <nav-menu></nav-menu>
        <router-view/>
    </div>
</template>

<script>
import navHeader from './components/NavHeader';
import navMenu from './components/NavMenu';

export default {
  components: {
    navHeader,
    navMenu,
  },
};
</script>

<style>
    #app {
        height: 100%;
        width: 100%;
    }
</style>
