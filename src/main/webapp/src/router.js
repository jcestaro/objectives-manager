import Vue from 'vue';
import Router from 'vue-router';
import ConsultEvidence from './views/ConsultEvidence';
import CreateEvidence from './views/CreateEvidence';
import ConsultObjective from './views/ConsultObjective';
import CreateObjective from './views/CreateObjective';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/objective/consult',
      name: 'consultObjective',
      component: ConsultObjective,
    },
    {
      path: '/objective/create',
      name: 'createObjective',
      component: CreateObjective,
    },
    {
      path: '/evidence/consult',
      name: 'consultEvidence',
      component: ConsultEvidence,
    },
    {
      path: '/evidence/create',
      name: 'createEvidence',
      component: CreateEvidence,
    },
  ],
});
