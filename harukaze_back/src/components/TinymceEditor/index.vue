<template>
  <div>
      <editor v-if="flag" class="tinymce" v-model="tinymceHtml" :init="editorInit"></editor>
  </div>
</template>

<script>
import Prism from 'prismjs'
import tinymce from 'tinymce'
import 'tinymce/themes/silver/theme'
import Editor from '@tinymce/tinymce-vue'
import 'tinymce/icons/default/icons'
import './plugin'

import { upload } from '@/api/upload'

export default {
    data() {
        return {
            editorInit: {
                selector: ".tinymce",
                language_url: '/tinymce/zh_CN.js',
                language: 'zh_CN',
                skin_url: '/tinymce/skins/ui/oxide-dark',
                end_container_on_empty_block: true,
                height: 900,
                // inline: true
                plugins: this.plugins,
                toolbar: this.toolbar,
                images_upload_handler: (blobInfo, success, failure) => {
                    this.handleImgUpload(blobInfo, success, failure)
                }

            },
            // tinymceHtml: '',
            flag: false
        }
    }, 
    computed: {
        tinymceHtml: {
            set(val) {
                let reg = /<pre.*?"/
                let reg1 = /<pre class="language/
                console.log(eval(reg1).test(val))
                console.log(val)
                if (eval(reg1).test(val)) {
                    this.value.contentHtml = val.toString().replace(eval(reg), "<pre class=\"line-numbers ")
                } else {
                    this.value.contentHtml = val
                }
                console.log(eval(reg1).test(val))
                this.$nextTick(() => {
                    Prism.highlightAll()
                })
            },
            get() {
                return this.value.contentHtml
            }
        }
    },
    props: {
        //传入一个value，使组件支持v-model绑定
        value: {
            type: Object,
            default: ' '
        },
        disabled: {
            type: Boolean,
            default: false
        },
        plugins: {
            type: [String, Array],
            default: 'toc textpattern forecolor charmap lists image imagetools media table wordcount contextmenu fullscreen code hr codesample emoticons help link preview searchreplace'
        },
        toolbar: {
            type: [String, Array],
            default: function () {
                return [
                    'undo redo |  formatselect | bold italic charmap forecolor | alignleft aligncenter alignright alignjustify toc | bullist numlist outdent indent ',
                    '| image media link table | removeformat hr codesample emoticons searchreplace | fullscreen preview code help'
                ]
            }
        }
    },
    methods: {
        handleImgUpload(blobInfo, success, failure) {
            if (blobInfo.blob().size/1024/1024 > 5) {
                failure("上传失败，图片大小请控制在5M以内")
            } else {
                let params = new FormData()
                params.append('image', blobInfo.blob())
                upload(params).then(res => {
                    success(res.data.data)
                }).catch(err => {
                    failure('上传出错，服务器开小差了呢')
                }) 
            }
        }
    },
    mounted() {
        tinymce.init({})
        Prism.highlightAll() 
    },
    activated() {
        this.flag = true
    },
    deactivated() {
        // tinymce.remove()
        this.flag = false
    },
    components: {Editor}
}
</script>

<style>

</style>