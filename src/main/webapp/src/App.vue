<template>
    <div id="app">
        <div fluid class="container-1">
            <div class="container-2">
                <b-navbar class="bg-dark" type="dark">
                    <b-row class="col-md-12">
                        <b-col class="col-md-2">
                            <b-dropdown text="Objectives" class="m-2">
                                <b-dropdown-item>
                                    <router-link to="/objective/consult">Consult</router-link>
                                </b-dropdown-item>

                                <b-dropdown-divider></b-dropdown-divider>
                                <b-dropdown-item>
                                    <router-link to="/objective/create">Create</router-link>
                                </b-dropdown-item>
                            </b-dropdown>
                        </b-col>

                        <b-col class="col-md-2">
                            <b-dropdown text="Evidences" class="m-2">

                                <b-dropdown-item>
                                    <router-link to="/evidence/consult">Consult</router-link>
                                </b-dropdown-item>

                                <b-dropdown-divider></b-dropdown-divider>

                                <b-dropdown-item>
                                    <router-link to="/evidence/create">Create</router-link>
                                </b-dropdown-item>

                            </b-dropdown>
                        </b-col>

                        <b-col class="col-md-2">
                            <b-navbar-brand class="m-md-2">Objectives Manager</b-navbar-brand>
                        </b-col>

                        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
                        <b-collapse id="nav-collapse" is-nav>
                            <b-navbar-nav class="ml-auto">
                                <b-nav-form>
                                    <b-col class="col-md-6">
                                        <b-form-input size="sm"
                                                      class="mr-sm-1"
                                                      placeholder="Search">
                                        </b-form-input>
                                    </b-col>
                                    <b-col class="col-md-2">
                                        <b-button size="sm"
                                                  class="mr-sm-5"
                                                  type="submit">
                                            Search
                                        </b-button>
                                    </b-col>
                                </b-nav-form>


                                <b-col>
                                    <b-nav-item-dropdown right>
                                        <template slot="button-content"><em>User</em></template>
                                        <b-dropdown-item>Profile</b-dropdown-item>
                                        <b-dropdown-item href="#">Sign Out</b-dropdown-item>
                                    </b-nav-item-dropdown>
                                </b-col>
                            </b-navbar-nav>
                        </b-collapse>
                    </b-row>
                </b-navbar>
            </div>

            <b-container fluid class="container-3">
                <router-view/>
            </b-container>

            <b-card-footer footer-bg-variant="dark"
                           footer-text-variant="light"
                           footer-border-variant="light">
                <b-row class="col-md-12">
                    <b-col>
                        <b-card-text>
                            &copy; 2019 github.com/jcestaro/objectivesmanager
                        </b-card-text>
                    </b-col>
                </b-row>
            </b-card-footer>
        </div>
    </div>
</template>

<script>
export default {};
</script>

<style scoped="scss">
    .container-1 {
        min-height: 100vh;
    }

    .container-2 {
        min-height: 10vh;
    }

    .container-3 {
        min-height: 84vh;
    }
</style>
