module.exports = {
  presets: [
    '@vue/cli-plugin-babel/preset'
  ],
  plugins: [
    ["prismjs", {
      "languages": ["javascript", "css", "markup", "java"],
      "plugins": ["line-numbers", "line-highlight"],
      "theme": "okaidia",
      "css": true
    }]
  ]
}
