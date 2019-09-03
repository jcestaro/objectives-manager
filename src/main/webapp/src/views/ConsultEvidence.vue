<template>
    <tabulator :data="data" :columns="columns"></tabulator>
</template>

<script>
    import Tabulator from "../components/Tabulator";

    export default {
        name: 'ConsultEvidence',
        components: {
            Tabulator
        },

        data: function () {
            return {
                data: {}
            }
        },
        computed: {
            columns() {
                return [
                    {title: 'name', field: 'name'},
                    {title: 'E-mail', field: 'email'},
                    {title: 'Phone', field: 'phone'}]
            }
        },
    };
</script>

<style scoped="scss">

</style>
