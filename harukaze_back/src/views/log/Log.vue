<template>
    <div>
        <my-breadcrumb :model="['日志管理','日志管理']"/>

        <el-card>
            <el-table
            :data="logs"
            style="width: 100%"
            border stripe>
                <el-table-column type="index" label="#"></el-table-column>
                <el-table-column prop="ip" label="ip地址" :width="130"></el-table-column>
                <el-table-column prop="module" label="访问模块" :width="80">
                    <template slot-scope="scope">
                        <el-tooltip class="item" effect="dark" :content="scope.row.module" placement="top-start">
                            <span>{{scope.row.module | ellipsis(4)}}</span>
                        </el-tooltip>
                    </template>
                </el-table-column>
                <el-table-column prop="method" label="访问方法" :width="90">
                    <template slot-scope="scope">
                        <el-tooltip class="item" effect="dark" :content="scope.row.method" placement="top-start">
                            <span>{{scope.row.method | ellipsis(11)}}</span>
                        </el-tooltip>
                    </template>
                </el-table-column>
                <el-table-column prop="operation" label="接口" :width="120">
                    <template slot-scope="scope">
                        <el-tooltip class="item" effect="dark" :content="scope.row.operation" placement="top-start">
                            <span>{{scope.row.operation | ellipsis(7)}}</span>
                        </el-tooltip>
                    </template>
                </el-table-column>
                <el-table-column label="访问日期" :width="160">
                    <template slot-scope="scope">
                            {{scope.row.createDate | dateFormat}}
                    </template>
                </el-table-column>
                <el-table-column prop="address" label="访问地址" :width="110">
                    <template slot-scope="scope">
                        <el-tooltip class="item" effect="dark" :content="scope.row.address" placement="top-start">
                            <span>{{scope.row.address | ellipsis(5)}}</span>
                        </el-tooltip>
                    </template>
                </el-table-column>
                <el-table-column prop="browser" label="使用浏览器" :width="190">
                    <template slot-scope="scope">
                        <el-tooltip class="item" effect="dark" :content="scope.row.browser" placement="top-start">
                            <span>{{scope.row.browser | ellipsis(30)}}</span>
                        </el-tooltip>
                    </template>
                </el-table-column>
                <el-table-column prop="params" label="访问参数" :width="120">
                    <template slot-scope="scope">
                        <el-tooltip class="item" effect="dark" :content="scope.row.params" placement="top-start">
                            <span>{{scope.row.params | ellipsis(15)}}</span>
                        </el-tooltip>
                    </template>
                </el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button icon="el-icon-delete" type="danger" size="mini" @click="deleteLog(scope.row.id)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>

        <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="page.pageNum"
        :page-sizes="[7, 10]"
        :page-size="page.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
        </el-pagination>
    </div>
</template>

<script>
import { getLogs,deleteLog } from '@/api/log'

export default {
    name: 'Log',
    data() {
        return {
            logs: [],
            page: {
                pageNum: 0,
                pageSize: 7
            },
            total: 0
        }
    },
    methods: {
        getLogs() {
            getLogs(this.page).then((res) => {
                this.logs = res.data.data
                this.total = res.data.total
            })
        },
        handleSizeChange(newSize) {
            this.page.pageSize = newSize
            this.getLogs()
        },
        handleCurrentChange(newNum) {
            this.page.pageNum = newNum
            this.getLogs()
        },
        deleteLog(id) {
            this.$confirm('此操作将删除该文章, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    deleteLog(id).then(() => {
                    this.$message.success("删除成功")
                    this.getLogs()
                })
                }).catch(err => {
                    this.$message.info('已取消')
                })
        }
    },
    created() {
        this.getLogs()
    }
}
</script>

<style lang="less" scoped>

</style>