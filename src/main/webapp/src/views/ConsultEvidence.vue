<template>
    <tabulator :data="data" :columns="columns"></tabulator>
</template>

<script>
import Tabulator from '../components/Tabulator';

export default {
  name: 'ConsultEvidence',
  components: {
    Tabulator,
  },

  data() {
    return {
      data: [{
        name: 'uasuhashuas',
        email: 'equeh@whueque.com',
        phone: '3445513',
      }],
    };
  },
  computed: {
    columns() {
      return [
        { title: 'name', field: 'name' },
        { title: 'E-mail', field: 'email' },
        { title: 'Phone', field: 'phone' }];
    },
  },
};
</script>

<style scoped="scss">

</style>
