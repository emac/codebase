#!groovy

GROUP_STANDBY = 'standby'
GROUPS = ['group1', 'group2', GROUP_STANDBY]
NODES = [ "${GROUPS[0]}": ['mobile1', 'mobile2'],
          "${GROUPS[1]}": ['mobile1', 'mobile1'],
          "${GROUPS[2]}": ['mobile5', 'mobile6']]

NODES.keySet().forEach({print it})

NODES.values().forEach({it.forEach({print it})})