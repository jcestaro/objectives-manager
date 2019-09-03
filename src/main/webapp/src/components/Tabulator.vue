<template>
    <div ref="table"></div>
</template>
<script>
    import 'tabulator-tables/dist/css/bootstrap/tabulator_bootstrap4.min.css'
    import Tabulator from 'tabulator-tables'

    export default {
        props: {
            data: {
                type: Array,
                required: true
            },
            columns: {
                type: Array,
                required: true
            },
            layout: String,
            selectable: Boolean,
            rowClick: Function,
        },
        data () {
            return {
                tabulator: null
            }
        },
        watch: {
            data: function (newVal, oldVal) {
                if(newVal !== oldVal){
                    this.tabulator.replaceData(newVal)
                }
            }
        },
        mounted () {
            this.tabulator = new Tabulator(this.$refs.table, {
                selectable: this.selectable,
                rowClick: this.rowClick,
                layout: this.layout,
                data: this.data, // link data to table
                columns: this.columns
            })
        }
    }
</script>
