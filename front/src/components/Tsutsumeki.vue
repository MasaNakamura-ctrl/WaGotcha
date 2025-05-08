<script setup>
import { onMounted,ref } from 'vue'
import { tsutsumekis, fetchTsutsumekis } from './tsutsumeki.js'
import axios from 'axios'

// データ一覧表示
onMounted(() => {
    fetchTsutsumekis()
})

// データ追加
const inputText = ref('')
const tsutsumeki = ref([])
async function addTsutsumeki(){
    if (inputText.value.trim()) {
        try{
            const response = await axios.post('http://localhost:8080/api/tsutsumekis',
                {
                    tsutsumeki:inputText.value
                }
            )
            tsutsumeki.value.push(response.data)
            inputText.value = ''
            await fetchTsutsumekis()
        } catch(error){
            console.error('投稿エラー',error);
        }
    }
}

// データ更新
let editingIndex = ref(null)
const editingText = ref('')
function editTsutsumeki(index){
    editingIndex.value = index
    editingText.value = tsutsumekis.value[index].tsutsumeki
}
async function completeEdittingTsutsumeki(index){
    try{
        const id = tsutsumekis.value[index].id
        await axios.put(`http://localhost:8080/api/tsutsumekis/${id}`,
            {
                tsutsumeki:editingText.value
            }
        )
        await fetchTsutsumekis()
        editingText.value = ''
        editingIndex.value = null
    } catch(error){
        console.error('更新エラー',error)
    }
}
function quitEdittingTsutsumeki(index){
    tsutsumekis.value[index].tsutsumeki = editingText.value
    editingIndex.value = null
}

// データ削除
async function deleteTsutsumeki(index){
    try {
        const id = tsutsumekis.value[index].id
        await axios.delete(`http://localhost:8080/api/tsutsumekis/${id}`)
        await fetchTsutsumekis()
    } catch(error){
        console.error('削除エラー', error)
    }
}

// WaGotcha押下(RESTAPIと連携次第別対応)
// WaGotchaされたインデックスを記録(RESTAPIと連携次第別対応)
const clickedIndices = ref(new Set())
function clickWaGotcha(index){
    clickedIndices.value.add(tsutsumekis.value[index]["id"])
}
</script>
<template>
    <v-container>
        <v-row justify="center">
            <v-col cols="12" sm="10" md="8" lg="6">
                <h1 class="text-h5 text-sm-h4 text-center">ツツメキ</h1>
                <div class="Tsutsumeki-create">
                    <textarea v-model="inputText" class="Tsutsumeki-text"
                    maxlength="140" placeholder="ツツメキしてWaGotchaしてもらおう！" rows="2">
                    </textarea>
                    <v-btn size="small" variant="outlined"
                    color="primary" @click="addTsutsumeki">ツツメキ</v-btn>
                </div>
                <div class="Tsutsumeki-posted" v-for="(tsutsumeki, index) in tsutsumekis">
                    <div v-if="editingIndex !== index">
                        <p class="Tsutsumeki-words">{{ tsutsumeki.tsutsumeki }}</p>
                        <v-btn size="x-small" variant="outlined"
                        color="primary" class="WaGotcha" @click="clickWaGotcha(index)"
                        :class= "{active: clickedIndices.has(tsutsumeki.id)}"
                        >WaGotcha</v-btn>
                        <v-btn size="x-small" variant="outlined" color="success" @click="editTsutsumeki(index)">改む</v-btn>
                        <v-btn size="x-small" variant="outlined" color="error" @click="deleteTsutsumeki(index)">消つ</v-btn>
                    </div>
                    <div v-else>
                        <textarea v-model="editingText"
                        class="Tsutsumeki-text Aratamu-text" maxlength="140" rows="2"></textarea>
                        <v-btn size="x-small" variant="outlined" color="primary"
                        @click="completeEdittingTsutsumeki(index)">記す</v-btn>
                        <v-btn size="x-small" variant="outlined" color="error"
                        @click="quitEdittingTsutsumeki(index)">辞む</v-btn>
                    </div>
                </div>
            </v-col>
        </v-row>
    </v-container>
</template>