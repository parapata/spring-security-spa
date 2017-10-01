const path = require('path');
const webpack = require('webpack');
const OUTPUT_PATH = './public'

module.exports = {
    entry: [
        './src/index.js',
    ],
    output: {
        path: path.resolve(__dirname, OUTPUT_PATH),
        filename: 'js/bundle.js',
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                enforce: 'pre',
                exclude: /node_modules/,
                use: [{
                    loader: 'babel-loader',
                    options: {
                        presets: [
                            ['es2015', {modules: false}],
                            'stage-0',
                            'react'],
                        comments: false,
                        compact: true,
                    },
                }],
            },
        ],
    },
    plugins: [
        new webpack.EnvironmentPlugin({
            NODE_ENV: 'debug', // use 'debug' unless process.env.NODE_ENV is defined
            DEBUG: true,
        }),
        new webpack.optimize.AggressiveMergingPlugin(),
        new webpack.optimize.OccurrenceOrderPlugin()
    ],
};
