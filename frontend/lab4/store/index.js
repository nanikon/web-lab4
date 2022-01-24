export const INDEX_MUTATIONS = {
  LOGIN: 'LOGIN',
  LOGOUT: 'LOGOUT',
}

export const state = () => ({
  login: "fdfdfdfdf",
  token: null, // JWT access token
})

export const mutations = {
  // store the logged in user in the state
  [INDEX_MUTATIONS.LOGIN] (state, { login, token }) {
    state.token = token
    state.login = login
  },

  // clear our the state, essentially logging out the user
  [INDEX_MUTATIONS.LOGOUT] (state) {
    state.token = null
    state.login = null
  },
}

export const actions = {
  login({ commit, state}, { login, token }) {
    commit(INDEX_MUTATIONS.LOGIN, { login, token })
  },

  logout ({ commit, state }) {
    commit(INDEX_MUTATIONS.LOGOUT)
  },
}

export const getters = {
  isAuthenticated: (state) => {
    return state.token && state.token !== ''
  },

  getToken: (state) => {
    return state.token
  },

  getUser: (state) => {
    return state.login
  }
}
