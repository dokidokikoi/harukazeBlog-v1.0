<template>
    <div>
        <div class="comment_body">
            <PublishComment :articleId="articleId"/>
            <CommentItem v-for="comment in comments" :key="comment.id" :comment="comment">
                    <PublishComment slot="recover" v-if="isRecover == comment.id" :parentId="comment.id" :articleId="articleId" :toUserName="comment.userVo.nickName">
                        <el-button size="mini" type="info" style="float: right;" @click="cancel">取消</el-button>
                    </PublishComment>
                    <ul style="margin-left: 50px;">
                        <CommentItem v-for="item in comment.children" :key="item.id" :comment="item">
                            <PublishComment slot="recover"  v-if="isRecover == item.id" :parentId="comment.id" :articleId="articleId" :toUserName="item.userVo.nickName">
                                <el-button size="mini" type="info" style="float: right;" @click="cancel">取消</el-button>
                            </PublishComment>
                        </CommentItem>
                    </ul>
            </CommentItem>
        </div>
        <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page.sync="page.pageNum"
        :page-size="page.pageSize"
        layout="prev, pager, next, jumper"
        background
        :total="total">
        </el-pagination>
    </div>
  
</template>

<script>
import { getCommentByArticleId, deleteComment } from '@/api/comment'
import CommentItem from '@/components/comment/CommentItem'
import PublishComment from '@/components/comment/PublishComment'

export default {
    name: 'SubComment',
    data() {
        return {
            page: {
                pageNum: 0,
                pageSize: 5
            },
            comments:[],
            total: 0,
            isRecover: ''
        }
    },
    props: ['articleId'],
    methods: {
        getComment() {
            getCommentByArticleId(this.articleId, this.page).then(res => {
                this.comments = res.data.data
                this.total = res.data.total
            })
        },
        cancel() {
            this.isRecover = ''
        },
        handleSizeChange(newSize) {
            this.page.pageSize = newSize
            this.getComment()
        },
        handleCurrentChange(newNum) {
            this.page.pageNum = newNum
            this.getComment()
        }
    },
    created() {
        this.getComment()
    },
    mounted() {
        this.$bus.$on('showRecover', (id) => {
            this.isRecover = id
        })
        this.$bus.$on('flush', (id) => {
            this.getComment()
        })
        this.$bus.$on('deleteComment', (id) => {
            deleteComment(id).then(() => {
                this.$message.success("删除成功")
                this.getComment()
            })
        })
    },
    components: {CommentItem, PublishComment},
    
}
</script>

<style lang="less" scoped>
    .comment_body {
        background-color: rgba(255, 255, 255, 0.9);
        border-radius: 4px;
        padding: 20px;
        box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
    }

    .publish {
        margin-bottom: 20px;
    }
</style>