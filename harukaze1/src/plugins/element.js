import Vue from 'vue'
import { Header,
        Menu,
        MenuItem,
        Submenu,
        Main,
        Footer,
        Container,
        Button,
        Input,
        Card,
        Row,
        Col,
        Image,
        Avatar,
        Pagination,
        Tag,
        Timeline,
        TimelineItem,
        Breadcrumb,
        BreadcrumbItem,
        Form,
        FormItem,
        MessageBox,
        Message,
 } from 'element-ui'
// import 'element-theme-chalk'
Vue.use(Header)
Vue.use(Menu)
Vue.use(MenuItem)
Vue.use(Submenu)
Vue.use(Main)
Vue.use(Footer)
Vue.use(Button)
Vue.use(Container)
Vue.use(Input)
Vue.use(Card)
Vue.use(Row)
Vue.use(Col)
Vue.use(Image)
Vue.use(Avatar)
Vue.use(Pagination)
Vue.use(Tag)
Vue.use(Timeline)
Vue.use(TimelineItem)
Vue.use(Breadcrumb)
Vue.use(BreadcrumbItem)
Vue.use(Form)
Vue.use(FormItem)

Vue.prototype.$message = Message
Vue.prototype.$confirm = MessageBox.confirm

