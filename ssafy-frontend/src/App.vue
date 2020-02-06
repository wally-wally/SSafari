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
import Header from '@/components/main/Header'
import Footer from '@/components/main/Footer'
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
    if (this.$session.get('token')){
      const token = this.$session.get('token')
      this.$store.dispatch('login', token)
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