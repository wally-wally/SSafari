<template>
    <v-flex justify-center>
        <div v-if="currentMemberId !== null">
            <div id="create-form-title">
                <span id="form-title">강의 등록 페이지</span>
            </div>
            <hr class="title-headline">
            <div class="title-top">
                <div class="create-title">Title</div>
                <!-- <div class="title-form"> -->
                <textarea v-model="title" id="title-form"></textarea>
                <!-- </div> -->
            </div>
            <label for="capacity">수강 인원 : </label>
            <input v-model="capacity" id="capacity" type="text">명<br>
            <label for="location">강의 지역 : </label>
            <input v-model="location" class="my-3" id="location" type="text">
            
    <v-row >
        <v-col cols="12" sm="6" md="4">
        <v-menu
            v-model="menu1"
            :close-on-content-click="false"
            :nudge-right="40"
            transition="scale-transition"
            offset-y
            min-width="290px"
        >
            <template v-slot:activator="{ on }">
            <v-text-field
                v-model="startdate"
                label="강의 시작일을 입력해 주세요"
                prepend-icon="event"
                readonly
                v-on="on"
            ></v-text-field>
            </template>
            <v-date-picker no-title v-model="startdate" @input="menu1=false"></v-date-picker>
        </v-menu>
        </v-col>
        <v-col cols="12" sm="6" md="4">
        <v-menu
            v-model="menu2"
            :close-on-content-click="false"
            :nudge-right="40"
            transition="scale-transition"
            offset-y
            min-width="290px"
        >
            <template v-slot:activator="{ on }">
            <v-text-field
                v-model="enddate"
                label="강의 종료일을 입력해 주세요"
                prepend-icon="event"
                readonly
                v-on="on"
            ></v-text-field>
            </template>
            <v-date-picker no-title v-model="enddate" @click.prevent="checkdate" @input="menu2=false"></v-date-picker>
        </v-menu>
        </v-col>
  </v-row>

            <textarea v-model="content" id="create-content" name="content"></textarea>
            <v-file-input :rules="rules" v-model="imgFile" label="File input" id="file" outlined dense accept="image/png, image/jpeg, image/bmp"></v-file-input>
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
            capacity : '',
            location : '',
            menu1:false,
            menu2:false,
            startdate : new Date().toISOString().substr(0, 10),
            enddate:'',
            imgFile : null,
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
            this.$router.push('/portfolio')
        },
        checkdate(){
            console.log(this.startdate)
            console.log(this.enddate)
        },
        create() {
            const formData = new FormData()
            formData.append('image',this.imgFile)
            axios.post('https://api.imgur.com/3/image', formData, {headers: { Authorization: 'Client-ID 347364b9fa38df3' }})
            .then(response=>{
                console.log(response.data.data)
                var imgLink = response.data.data.link
                
                var portfolioData = {
                    title: this.title,
                    body: this.content,
                    memberid: this.$session.get('data')['memberid'],
                    img: imgLink,
                    capacity : this.capacity,
                    location : this.location,
                    startdate : this.startdate,
                    enddate : this.enddate,
                }
                console.log(portfolioData)

                axios.post('api/portfolio', portfolioData)
                    .then(response => {
                        if(response.status === 200){
                            this.$router.push('/portfolio')
                        }
                    })
            })
            .catch(error =>{
                console.log(error)
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