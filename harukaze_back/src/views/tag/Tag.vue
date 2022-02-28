<template>
  <div>
      <my-breadcrumb :model="['文章管理', '标签管理']"/>

      <el-card class="box">
        <div id="myChart" :style="{width: '1200px', height: '500px'}"></div>

        <el-button type="primary" @click="addDialogVisible=true" style="margin-left:20px">添加标签</el-button>
        <el-table :data="tags">
            <el-table-column type="index" label="#"></el-table-column>
            <el-table-column label="标签名" prop="tagName"></el-table-column>
            <el-table-column label="avatar" prop="avatar"></el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">                     
                    <el-button icon="el-icon-edit"  type="primary" size="mini" @click="showEditDialog(scope.row)">修改</el-button>
                    <el-button icon="el-icon-delete" type="danger" size="mini" @click="deleteTag(scope.row.id)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
      </el-card>

      <el-dialog
        title="修改标签"
        :visible.sync="editDialogVisible"
        width="40%"
        @close="closeHandler">
            <el-form :model="tagForm" ref="tagForm" label-width="60px">
                <el-form-item label="标签名">
                    <el-input type="text" v-model="tagForm.tagName"></el-input>
                </el-form-item>
                <el-form-item label="avatar">
                    <el-input type="text" v-model="tagForm.avatar"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="editTag">确 定</el-button>
            </span>
      </el-dialog>

      <el-dialog
        title="添加标签"
        :visible.sync="addDialogVisible"
        width="40%"
        @close="closeHandler">
            <el-form :model="tagForm" ref="tagForm" label-width="60px">
                <el-form-item label="标签名">
                    <el-input type="text" v-model="tagForm.tagName"></el-input>
                </el-form-item>
                <el-form-item label="avatar">
                    <el-input type="text" v-model="tagForm.avatar"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveTag">确 定</el-button>
            </span>
      </el-dialog>
  </div>
</template>

<script>
import { tagReport,getTags,saveTag,editTag,deleteTag } from '@/api/tag'
import { navHighLight } from '@/mixin'

export default {
    name: 'Tag',
    data() {
        return {
            reportData:{
                tagNames: [],
                articleCounts: [],
                viewCounts: [],
                commentCounts: []
            },
            tags: [],
            editDialogVisible: false,
            addDialogVisible: false,
            tagForm: {
                id: '',
                tagName: '',
                avatar: ''
            }
        }
    },
    methods: {
        getReport() {
            this.reportData = {
                tagNames: [],
                articleCounts: [],
                viewCounts: [],
                commentCounts: []
            }
            tagReport().then(res => {
                const data = res.data.data
                data.forEach(element => {
                    this.reportData.tagNames.push(element.name)
                    this.reportData.articleCounts.push(element.articleCounts)
                    this.reportData.viewCounts.push(element.viewCounts)
                    this.reportData.commentCounts.push(element.commentCounts)
                });
                this.drawLine()
            })
        },
        drawLine() {
            // 基于准备好的dom，初始化echarts实例
            var myChart = this.$echarts.init(document.getElementById('myChart'));

            let option = {
                title: {
                    text: 'Tag Report'
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                    type: 'shadow'
                    }
                },
                legend: {
                    data: ['文章数', '阅读数', '评论数']
                },
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    left: 'right',
                    top: 'center',
                    feature: {
                    mark: { show: true },
                    dataView: { show: true, readOnly: false },
                    magicType: { show: true, type: ['line', 'bar', 'stack'] },
                    restore: { show: true },
                    saveAsImage: { show: true }
                    }
                },
                xAxis: 
                    {
                    type: 'category',
                    axisTick: { show: false },
                    data: this.reportData.tagNames
                    }
                ,
                yAxis: 
                    {
                    type: 'value'
                    }
                ,
                series: [
                    {
                        name: '文章数',
                        type: 'bar',
                        barGap: 0,

                        emphasis: {
                            focus: 'series'
                        },
                        data: this.reportData.articleCounts
                    },
                    {
                    name: '阅读数',
                    type: 'bar',
   
                    emphasis: {
                        focus: 'series'
                    },
                    data: this.reportData.viewCounts
                    },
                    {
                    name: '评论数',
                    type: 'bar',

                    emphasis: {
                        focus: 'series'
                    },
                    data: this.reportData.commentCounts
                    }
                ]
            }
            // 绘制图表
            myChart.setOption(option)
        },
        getTags() {
            getTags().then(res => {
                this.tags = res.data.data
            })
        },
        showEditDialog(row) {
            this.tagForm.id = row.id
            this.tagForm.tagName = row.tagName
            this.tagForm.avatar = row.avatar

            this.editDialogVisible = true
        },
        deleteTag(id) {
            this.$confirm('此操作将删除该标签, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    deleteTag(id).then(() => {
                        this.$message.success("删除成功")
                        this.editDialogVisible = false
                        this.getTags()
                        this.getReport()
                    })
                    
                }).catch(err => {
                    this.$message.info('已取消')
                    this.editDialogVisible = false
                })
        },
        closeHandler() {
            this.tagForm = {
                id: '',
                tagName: '',
                avatar: ''
            }
        },
        saveTag() {
            saveTag(this.tagForm).then(() => {
                this.$message.success("添加标签成功")
                this.addDialogVisible = false
                this.getTags()
                this.getReport()
            })
        },
        editTag() {
            editTag(this.tagForm).then(() => {
                this.$message.success("修改标签成功")
                this.editDialogVisible = false
                this.getTags()
                this.getReport()
            })
        }
    },
    mixins: [navHighLight],
    created() {
        this.getReport()
        this.getTags()
    },
    mounted() {
        
    }
}
</script>

<style>

</style>