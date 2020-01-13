'use strict';

const { move, compare, constructPath, getAdjMatrix } = require('./helpers');

module.exports = function DFS(graph, startNode, endNodes) {

  let visited = [];
  let parents = {};
  let stack = [];

  // start node
  parents[startNode] = null;
  visited[startNode] = true;
  stack.push(startNode);
  console.log(`  Adding node ${startNode} to stack.`);

  // while we have elements on stack we iterate
  while(stack.length > 0) {

    // stack.peek()
    let currentNode = stack[stack.length - 1];

    // if we found the final node
    if(endNodes.some(node => compare(node, currentNode))) {
      return constructPath(currentNode, parents);
    }

    let found = false;

    // check adjacent nodes (children)
    for(let direction of ['up', 'right', 'down', 'left']) {
                          
      let adjacent = move(currentNode, direction);

      // if not visited yet and is valid node then add it to stack
      if(!visited[adjacent] && graph.some(node => compare(node, adjacent))) {
        visited[adjacent] = true;
        parents[adjacent] = currentNode;
        stack.push(adjacent);
        console.log(`  Adding node ${adjacent} to stack.`);

        found = true;
        break;
      }
    }
    
    if(!found) {
      stack.pop();
      console.log(`  Removing node ${currentNode} from stack.`);
    }
  }

  console.log('-------------------------------------------------');
  
}
