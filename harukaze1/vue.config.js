const path = require('path')
function resolve (dir) {
  return path.join(__dirname, dir)
}

module.exports = {
	lintOnSave:false, //关闭语法检查
	// publicPath: process.env.NODE_ENV === "production" ? "/newProject/" : "/",
	// 省略其他...
  	// 与devServer配置项同级
	chainWebpack: config => {
		// set svg-sprite-loader
		config.module
		.rule('svg')
		.exclude.add(resolve('src/icons'))
		.end()
		config.module
		.rule('icons')
		.test(/\.svg$/)
		.include.add(resolve('src/icons'))
		.end()
		.use('svg-sprite-loader')
		.loader('svg-sprite-loader')
		.options({
			symbolId: 'icon-[name]'
		})
		.end()

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
				vuex: 'Vuex',
				nprogress: 'NProgress',
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

