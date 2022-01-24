export default {
  // Target: https://go.nuxtjs.dev/config-target
  target: 'static',

  // Global page headers: https://go.nuxtjs.dev/config-head
  head: {
    title: 'lab4',
    htmlAttrs: {
      lang: 'ru'
    },
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: '' },
      { name: 'format-detection', content: 'telephone=no' }
    ],
    link: [
      {
        as: 'style',
        rel: 'stylesheet preload prefetch',
        href: '/fonts/fonts.css',
      },
      { rel: 'icon',
        type: 'image/x-icon',
        href: '/favicon.ico' }
    ]
  },

  // Global CSS: https://go.nuxtjs.dev/config-css
  css: [
    '@/assets/styles/global',
    //'reset-css/reset.css',
  ],

  // Plugins to run before rendering page: https://go.nuxtjs.dev/config-plugins
  plugins: [
  ],

  // Auto import components: https://go.nuxtjs.dev/config-components
  components: true,

  // Modules for dev and build (recommended): https://go.nuxtjs.dev/config-modules
  buildModules: [
  ],

  // Modules: https://go.nuxtjs.dev/config-modules
  modules: [
    '@nuxtjs/style-resources',
    '@nuxt/http'
  ],

  styleResources: {
    scss: ['./assets/styles/variables.scss'],
  },

  http: {
    host: 'localhost',
    port: '7656',
    prefix: '/api',
    baseUrl: 'http://localhost:7656/api'
  },

  // Build Configuration: https://go.nuxtjs.dev/config-build
  build: {
    vendor: ['svg']
  },

  generate: { fallback: true }
}
