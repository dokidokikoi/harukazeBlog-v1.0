<template>
    <div>
        <ul class="archives" v-for="(item, i) in archives" :key="i">
            <li>
                <span>
                    <h3 class="year">{{item.year}}年</h3>
                </span>
                <ul class="mon_list" v-for="(element, I) in item.e" :key="I">
                    <li>
                        <span class="mon">{{element.month}}月({{element.e.length}}篇文章)</span>
                        <ul class="day" v-for="blog in element.e" :key="blog.id">
                            <router-link :to="`blog/${blog.id}`"><span style="margin-right: 10px">{{blog.createDate | timeFormat}}</span>{{blog.title}}</router-link>
                        </ul>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</template>

<script>
import { getArchive } from '@/api/article'

export default {
    name: 'TimeStamp',
    data() {
        return {
            archives: []
        }
    },
    methods: {
        getArchive() {
            this.$store.commit('LOADING')
            getArchive(this.page).then(res => {
                const data = res.data.data
                data.forEach(element => {
                    if (this.archives.length == 0 || this.archives[this.archives.length-1].year != new Date(element.createDate).getFullYear()) {
                        this.addYear(element)
                    } else if (this.archives[this.archives.length-1].e[this.archives[this.archives.length-1].e.length - 1].month != (new Date(element.createDate).getMonth() + 1 + '').padStart(2, '0')) {
                        this.addMonth(element)
                    } else {
                        this.add(element)
                    }
                });
                this.$store.commit('COMPLETE')
                this.$store.dispatch('toTop')
            })
        },
        addYear(data) {
            this.archives.push({
                year: new Date(data.createDate).getFullYear(),
                e: [
                    {
                        month: (new Date(data.createDate).getMonth() + 1 + '').padStart(2, '0'), 
                        e: [
                            data
                        ]
                    }
                ]
            })
        },
        addMonth(data) {
            this.archives[this.archives.length - 1].e.push({
                month: (new Date(data.createDate).getMonth() + 1 + '').padStart(2, '0'), 
                e: [
                    data
                ]
            })
        },
        add(data) {
            this.archives[this.archives.length-1].e[this.archives[this.archives.length-1].e.length - 1].e.push(data)
        }
    },
    created() {
        this.getArchive()
    }
}
</script>

<style scoped lang="less">
    .archives {
        box-sizing: border-box;
    }

    span {
        color: #ff69b4;
    }

    .year {
        margin: 0;
        margin-left: 100px;
        clear: both;
    }

    ul {
        position: relative;
        padding: 10px 0;
        display: inline-block;
        vertical-align: middle;
        list-style-type: none;
        margin: 0;
        padding: 0;
        width: 100%;
    }

    li {
        margin: 0;
        padding: 0;
    }

    a {
        text-decoration: none;
        color: black;
    }

    .mon {
        position: relative;
    }

    .mon::before {
        position: absolute;
        left: 113px;
        background-color: #fff;
        height: 18px;
        width: 18px;
        border-radius: 50%;
        top: 3px;
        content: '';
        box-shadow: 1px 1px 1px #bbb;
    }

    .mon::after {
        position: absolute;
        left: 116px;
        height: 12px;
        width: 12px;
        border-radius: 6px;
        top: 6px;
        content: '';
        background-color: #ff69b4;
        z-index: 100;
    }

    .mon_list {
        padding: 10px 0;
        
    }

    .mon_list::before {
        max-height: 100%;
        height: 100%;
        width: 4px;
        background-color: #ff69b4;
        position: absolute;
        left: 120px;
        content: '';
        top: 0;
    }

    .day {
        padding-left: 140px;
        position: relative;

        a:hover {
            color: #ff69b4;
            
        }
    }

    .day::before {
        position: absolute;
        left: 116px;
        background-color: #fff;
        height: 12px;
        width: 12px;
        border-radius: 50%;
        top: 4px;
        content: '';
        box-shadow: 1px 1px 1px #bbb;
    }

    .day::after {
        position: absolute;
        left: 118px;
        height: 8px;
        width: 8px;
        border-radius: 6px;
        top: 6px;
        content: '';
        background-color: #ff69b4;
        z-index: 100;
    }
</style>