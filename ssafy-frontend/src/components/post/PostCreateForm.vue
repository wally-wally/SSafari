<template>
    <v-flex justify-center>
        <div v-if="currentMemberId !== null">
            <div id="create-form-title">
                <span id="form-title">Post Form</span>
            </div>
            <hr class="title-headline">
            <div class="title-top">
                <div class="create-title">Title</div>
                <!-- <div class="title-form"> -->
                <textarea v-model="title" id="title-form"></textarea>
                <!-- </div> -->
            </div>
            <textarea v-model="content" id="create-content" name="content"></textarea>
            <!-- <input type="file" name="file" id="file"><br> -->
            <!-- <button type="submit">작성</button> -->
            <v-btn class="mr-5" color="#f7b157" @click="create">작성</v-btn>
            <v-btn color="error" @click="goBack()">취소</v-btn>
        </div>
        <div v-else>
            <h1 style="text-align:center; color:red">접근 권한이 없습니다!</h1>
            <v-btn :to="{ name: 'home'}" style="margin:0 auto; display:block; width: 300px;" class="red"><h3>Home으로 이동</h3></v-btn>
        </div>
    </v-flex>
</template>

<script>
import axios from 'axios'
import router from '@/router.js'

export default {
    data() {
        return {
            currentMemberId: null,
            title: '',
            content : '',
            from : null,
            rules: [
                function(value) {
                    const extension = value.name.toString().split('.')[1]
                    if (!(extension === 'jpg' || extension === 'png' || extension === 'bmp')) {
                        // const fileValue = document.querySelector('#file')
                        alert("Please upload image file.(available jpg, png, bmp flie)")
                        document.querySelector('#file').value = '' // 실질적인 경로로 잡힌 파일 삭제
                        const filename = document.querySelector('.v-file-input__text')
                        filename.innerText = '' // html 문서에서 보이는 파일 이름도 삭제
                    }
                }
                // value => value.name. || "Upload Image must have image extension(jpg, png, bmp)."
            ]
        }
    },
    beforeRouteEnter(to, from, next) {
        next((vm) => {
            vm.from = from;
        });
    },
    mounted() {
        this.currentMemberId = this.$session.get('data')['memberid']
    },
    methods : {
        goBack() {
            const formTitle = document.querySelector('#form-title')
            if (formTitle.innerText === 'Post Form') {
                this.$router.push('/post')
            } else {
                this.$router.push('/portfolio')
            }
        },
        create() {
            var postData = {
                title: this.title,
                body: this.content,
                memberid: this.$session.get('data')['memberid']
            }
            axios.post('api/post', postData)
                .then(response => {
                    if(response.status === 200){
                        this.$router.push('/post')
                    }
                })
        }
    }
}
</script>

<style>
#create-form-title {
    font-size: 2em;
    font-family: 'Do Hyeon', sans-serif;
    text-align: center;
    padding-top: 6px;
    padding-bottom: 10px;
}

.title-headline {
    border: none;
    border: 1px dashed lightgrey;
    margin-bottom: 20px;
}

.title-top {
    display: flex;
    margin-bottom: 20px;
}

.create-title {
    font-size: 20px;
    font-weight: bold;
    text-align: center;
    width: 15%;
}

@media (min-width: 555px) and (max-width: 880px) {
    .create-title {
        width: 10%;
    }
}

@media (min-width: 881px) {
    .create-title {
        width: 6%;
    }
}

#title-form {
    border: 1px solid black;
    width: 100%;
    height: 30px;
    resize: none;
    box-shadow: 5px 5px 7px grey;
}

#create-content {
    width: 100%;
    height: 300px;
    resize: none;
    border: 1px solid black;
    box-shadow: 5px 5px 7px grey;
    margin-bottom: 20px;
}
</style>