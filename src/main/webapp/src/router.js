import Vue from 'vue';
import Router from 'vue-router';
import Home from './views/Home';
import Objective from './components/Objective';
import Evidence from './components/Evidence';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
    },
    {
      path: '/objective',
      name: 'objective',
      component: Objective,
    },
    {
      path: '/evidence',
      name: 'evidence',
      component: Evidence,
    },
  ],
});
