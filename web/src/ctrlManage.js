const makeRequire = function (name) {
  return r => require.ensure([], () => r(require(`./controllers/${name}.vue`)), 'manager')
}

export default {
  Layout: makeRequire('Manage'),
  Login: makeRequire('ManageLogin')
}
