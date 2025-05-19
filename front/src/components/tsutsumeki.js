import { ref, onMounted } from 'vue'
import axios from 'axios'
export const tsutsumekis = ref([])
export async function fetchTsutsumekis() {
    try {
        const response = await axios.get('http://localhost:8080/api/tsutsumekis')
        tsutsumekis.value = response.data
    } catch (error) {
    console.error('タスク取得エラー:', error)
    }
}
export async function fetchTsutsumekisById(index){
    try {
        const response = await axios.get(`http://localhost:8080/api/tsutsumekis/${index}`)
        tsutsumekis.value = response.data
    } catch (error) {
    console.error('タスク取得エラー:', error)
    }
}