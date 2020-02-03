<template>
  <v-app>
    <Header></Header>
    <v-content >
      <router-view />
    </v-content>
    <Footer></Footer>
  </v-app>
</template>

<script>
import Header from '@/components/Header'
import Footer from '@/components/Footer'
export default {
  name: "App",
  components: {
    Header,
    Footer
  },
  data() {
    return {
    };
  },
  mounted(){
    if (this.$session.get('data')){
      this.$store.commit('loginSuccess', this.$session.get('data'))
    }
  }
};
</script>
<style>
*:focus {
    outline: none;
}
  a {
    text-decoration:none;
  }
</style>