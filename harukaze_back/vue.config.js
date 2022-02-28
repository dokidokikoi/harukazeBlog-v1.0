
module.exports = {
	lintOnSave:false, //关闭语法检查
	chainWebpack: config => {
		config.when(process.env.NODE_ENV === 'production', config => {
			config.entry('app').clear().add('./src/main-prod.js')
			config.plugin('html').tap(args => {
				args[0].isProd = true
				return args
			})

			config.set('externals', {
				vue: 'Vue',
				'vue-router': 'VueRouter',
				axios: 'axios',
				echarts: 'echarts',
				vuex: 'Vuex',
				nprogress: 'NProgress',
				tinymce: 'tinymce',
			})
		})
		config.when(process.env.NODE_ENV === 'development', config => {
			config.entry('app').clear().add('./src/main-dev.js')

			config.plugin('html').tap(args => {
				args[0].isProd = false
				return args
			})
		})
	},
	// devServer: {
	// 	proxy: 'https://120.76.201.50:8081'
	// },
}