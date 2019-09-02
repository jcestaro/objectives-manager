<template>
    <b-container fluid>
        <b-navbar class="bg-dark" toggleable="lg" type="dark">
            <b-navbar-brand>Menu</b-navbar-brand>
            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
            <b-collapse id="nav-collapse" is-nav>
                <b-navbar-nav class="ml-auto">
                    <b-nav-form>
                        <b-form-input size="sm" class="mr-sm-1" placeholder="Search"></b-form-input>
                        <b-button size="sm" class="mr-sm-5" type="submit">Search</b-button>
                    </b-nav-form>

                    <b-nav-item-dropdown right>
                        <template slot="button-content"><em>User</em></template>
                        <b-dropdown-item href="#">Profile</b-dropdown-item>
                        <b-dropdown-item href="#">Sign Out</b-dropdown-item>
                    </b-nav-item-dropdown>
                </b-navbar-nav>
            </b-collapse>
        </b-navbar>
        <b-card no-body>
            <b-tabs pills card vertical>
                <b-tab title="Objectives" active>
                    <router-link :to="{ path: '/objective' }"></router-link>
                </b-tab>
                <b-tab title="Evidences">
                    <router-link :to="{ path: '/evidence' }"></router-link>
                </b-tab>
            </b-tabs>
        </b-card>
    </b-container>
</template>

<script>
export default {
  name: 'Home',
};
</script>
