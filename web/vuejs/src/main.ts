import './assets/main.css'

import {createApp} from 'vue'
import App from './App.vue'
import HelloComponent from "@/components/HelloComponent.vue";
import SlotDemo from "@/components/SlotDemo.vue";

const app = createApp(App)

app.component("HelloComponent", HelloComponent)
app.component("SlotDemo", SlotDemo)

app.mount('#app')
