module.exports = {
    root: true,
    parserOptions: {
        "ecmaVersion": 7,
        "parser": "babel-eslint",
        sourceType: 'module'
    },
    // required to lint *.vue files
    plugins: [
        'html'
    ],
    // add your custom rules here
    'rules': {
        // allow debugger during development
        'no-debugger': process.env.NODE_ENV === 'production' ? 2 : 0
    }
}

 
