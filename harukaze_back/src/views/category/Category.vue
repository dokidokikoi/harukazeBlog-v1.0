<template>
  <div>
      <my-breadcrumb :model="['文章管理', '分类管理']"/>

      <el-card>
          <div id="myChart" :style="{width: '1200px', height: '500px'}"></div>

          <el-button type="primary" @click="addDialogVisible=true" style="margin-left:20px">添加分类</el-button>
          <el-table :data="categorys">
            <el-table-column type="index" label="#"></el-table-column>
            <el-table-column label="分类名" prop="categoryName" width="120"></el-table-column>
            <el-table-column label="avatar" prop="avatar" width="280"></el-table-column>
            <el-table-column label="描述" prop="description"></el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">                     
                    <el-button icon="el-icon-edit"  type="primary" size="mini" @click="showEditDialog(scope.row)">修改</el-button>
                    <el-button icon="el-icon-delete" type="danger" size="mini" @click="deleteCategory(scope.row.id)">删除</el-button>
                </template>
            </el-table-column>
          </el-table>

        <el-dialog
        title="修改分类"
        :visible.sync="editDialogVisible"
        width="40%"
        @close="closeHandler">
            <el-form :model="categoryForm" label-width="60px">
                <el-form-item label="分类名">
                    <el-input type="text" v-model="categoryForm.categoryName"></el-input>
                </el-form-item>
                <el-form-item label="avatar">
                    <el-input type="text" v-model="categoryForm.avatar"></el-input>
                </el-form-item>
                <el-form-item label="描述">
                    <el-input type="text" v-model="categoryForm.description"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="editCategory">确 定</el-button>
            </span>
      </el-dialog>

      <el-dialog
        title="添加分类"
        :visible.sync="addDialogVisible"
        width="40%"
        @close="closeHandler">
            <el-form :model="categoryForm" label-width="60px">
                <el-form-item label="分类名">
                    <el-input type="text" v-model="categoryForm.categoryName"></el-input>
                </el-form-item>
                <el-form-item label="avatar">
                    <el-input type="text" v-model="categoryForm.avatar"></el-input>
                </el-form-item>
                <el-form-item label="描述">
                    <el-input type="text" v-model="categoryForm.description"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveCategory">确 定</el-button>
            </span>
      </el-dialog>
      </el-card>
  </div>
</template>

<script>
import { getCategoryReport,getCategoryList,saveCategory,editCategory,deleteCategory } from '@/api/category'
import { navHighLight } from '@/mixin'
export default {
    name: 'Category',
    data() {
        return {
            reportData: {
                indicator: [
                    { name: '文章数', max: 500 },
                    { name: '阅读数', max: 500 },
                    { name: '评论数', max: 500 },
                ],
                categorys: [],
                data: []
            },
            categorys: [],
            editDialogVisible: false,
            addDialogVisible: false,
            categoryForm: {
                id: '',
                categoryName: '',
                avatar: '',
                description: ''
            }
        }
    },
    methods: {
        getCategoryReport() {
            this.reportData =  {
                indicator: [
                    { name: '文章数', max: 500 },
                    { name: '阅读数', max: 500 },
                    { name: '评论数', max: 500 },
                ],
                categorys: [],
                data: []
            }
            getCategoryReport().then(res => {
                const data = res.data.data
                let maxArticle = 0
                let maxView = 0
                let maxComment = 0
                data.forEach(element => {
                    this.reportData.categorys.push(element.name)
                    this.reportData.data.push({
                        value: [element.articleCounts, element.viewCounts, element.commentCounts],
                        name: element.name
                    })
                    maxArticle = maxArticle < element.articleCounts ? element.articleCounts : maxArticle
                    maxView = maxView < element.viewCounts ? element.viewCounts : maxView
                    maxComment = maxComment < element.commentCounts ? element.commentCounts : maxComment
                });
                this.reportData.indicator[0].max = maxArticle * 1.3
                this.reportData.indicator[1].max = maxView * 1.3
                this.reportData.indicator[2].max = maxComment * 1.3

                this.drawLine()
            })
        },
        drawLine() {
            // 基于准备好的dom，初始化echarts实例
            var myChart = this.$echarts.init(document.getElementById('myChart'));

            let option = {
                title: {
                    text: 'Category report'
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: { // 坐标轴指示器，坐标轴触发有效
                        type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                radar: {
                    radius: '66%',
                    center: ['50%', '42%'],
                    splitNumber: 8,
                    splitArea: {
                        areaStyle: {
                        color: 'rgba(127,95,132,.3)',
                        opacity: 1,
                        shadowBlur: 45,
                        shadowColor: 'rgba(0,0,0,.5)',
                        shadowOffsetX: 0,
                        shadowOffsetY: 15
                        }
                    },
                    indicator: this.reportData.indicator
                },
                legend: {
                    left: 'center',
                    bottom: '10',
                    data: this.reportData.categorys
                },
                series: [{
                    type: 'radar',
                    symbolSize: 0,
                    areaStyle: {
                        normal: {
                        shadowBlur: 13,
                        shadowColor: 'rgba(0,0,0,.2)',
                        shadowOffsetX: 0,
                        shadowOffsetY: 10,
                        opacity: 1
                        }
                    },
                    data: this.reportData.data,
                    tooltip: {
                        trigger: 'item'
                    },
                    animationDuration: 2000
                }]
            }
            // 绘制图表
            myChart.setOption(option)
        },
        getCategoryList() {
            getCategoryList().then(res => {
                this.categorys = res.data.data
            })
        },
        showEditDialog(row) {
            this.categoryForm.id = row.id
            this.categoryForm.categoryName = row.categoryName
            this.categoryForm.avatar = row.avatar
            this.categoryForm.description = row.description

            this.editDialogVisible = true
        },
        deleteCategory(id) {
            this.$confirm('此操作将删除该分类, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    deleteCategory(id).then(() => {
                        this.$message.success("删除成功")
                        this.getCategoryList()
                    }) 
                }).catch(err => {
                    this.$message.info('已取消')
                })
        },
        closeHandler() {
            this.categoryForm = {
                id: '',
                categoryName: '',
                avatar: '',
                description: ''
            }
        },
        saveCategory() {
            saveCategory(this.categoryForm).then(res => {
                this.$message.success("添加分类成功")
                this.addDialogVisible = false
                this.getCategoryList()
                this.getCategoryReport()
            })
        },
        editCategory() {
            editCategory(this.categoryForm).then(res => {
                this.$message.success("修改分类成功")
                this.editDialogVisible = false
                this.getCategoryList()
                this.getCategoryReport()
            })
        }
    },
    mixins: [navHighLight],
    created() {
        this.getCategoryReport()
        this.getCategoryList()
    },
}
</script>

<style>

</style>