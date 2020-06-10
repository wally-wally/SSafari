<template>
  <div>
    <SubBoardHeader/>
    <router-view></router-view>
  </div>
</template>

<script>
import SubBoardHeader from '../components/board/boardCommonForm/SubBoardHeader'

export default {
	name: 'BoardPage',
	components: {
    SubBoardHeader
	}
}
</script>
