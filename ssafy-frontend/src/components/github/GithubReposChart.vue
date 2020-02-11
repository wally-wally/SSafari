<script>
import { Bar } from 'vue-chartjs'
export default {
  name: 'GithubReposChart',
  extends: Bar,
  props: {
    datacollection: { type: Object, defalut: null }
  },
  data() {
    return {
    }
  },
  mounted() {
    this.renderChart(this.datacollection, {})
  }
}
</script>

<style>

</style>