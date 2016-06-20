#!groovy

GROUP_STANDBY = 'standby'
GROUPS = ['group1', 'group2', GROUP_STANDBY]
NODES = [ "${GROUPS[0]}": ['mobile1', 'mobile2'],
          "${GROUPS[1]}": ['mobile1', 'mobile1'],
          "${GROUPS[2]}": ['mobile5', 'mobile6']]

print GROUPS.size()

GROUPS.forEach({print it})

NODES.keySet().forEach({print it})

NODES.values().forEach({it.forEach({print it})})

forEach(GROUPS, {print it})

NODES.collect({k,v->v}).forEach({print it})

def forEach(_list, Closure _do) {
    for (int i = 0; i < _list.size(); i++) {
        _do(_list[i])
    }
}