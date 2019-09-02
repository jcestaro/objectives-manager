<template>
    <div class="container-fluid">
        <b-nav vertical>
            <b-nav>
                <b-nav-item-dropdown
                        id="my-nav-dropdown"
                        text="Objectives"
                        toggle-class="nav-link-custom"
                        right>
                    <b-dropdown-item>Consult</b-dropdown-item>
                    <b-dropdown-item>Create</b-dropdown-item>
                </b-nav-item-dropdown>
            </b-nav>
            <b-nav>
                <b-nav-item-dropdown
                        id="my-nav-dropdown"
                        text="Evidences"
                        toggle-class="nav-link-custom"
                        right>
                    <b-dropdown-item>Consult</b-dropdown-item>
                    <b-dropdown-item>Create</b-dropdown-item>
                </b-nav-item-dropdown>
            </b-nav>
        </b-nav>
    </div>
</template>

<script>
export default {
  name: 'navMenu',
};
</script>
