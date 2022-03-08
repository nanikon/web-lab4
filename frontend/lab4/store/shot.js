export const SHOT_MUTATIONS = {
  REFRESH: 'REFRESH'
}

export const state = () =>({
  shotList: []
})

export const mutations = {
  [SHOT_MUTATIONS.REFRESH] (state, { shotList }) {
    state.shotList = shotList
  }
}

export const actions = {
  setShots({ commit, state}, { shotList }) {
    commit(SHOT_MUTATIONS.REFRESH, shotList)
  }
}
