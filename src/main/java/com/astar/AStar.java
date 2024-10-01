package com.astar;

import java.awt.*;
import java.util.ArrayList;

//By: Brandon Beckwith
class AStar {

    // NOTE: This is the only class you need to edit.
    //Feel free to add whatever methods you need here!
    public static int manhattanDistance(Point a, Point b){
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    /**
     * Runs A star on the given board
     * @param board The board to run A Star on
     * @param start The starting Point
     * @param end The ending Point
     * @return The spaces in order from the start Point to the end Point
     */
    public static ArrayList<Space> findPath(Board board, Point start, Point end) {

        //Setup an ArrayList to hold the path to  return to the GUI
        ArrayList<Space> path = new ArrayList<Space>();

        // TODO: Implement AStar

        ArrayList<Space> openList = new ArrayList<>();
        ArrayList<Space> closedList = new ArrayList<>();

        Space startSpace = board.getSpace(start.x, start.y);
        startSpace.setG(0);
        startSpace.setH(manhattanDistance(start, end));
        openList.add(startSpace);

        while (!openList.isEmpty()){
            Space current = openList.get(0);
            for (Space space : openList){
                if (space.getF() < current.getF()){
                    current = space;
                }
            }

            if (current.getPoint().equals(end)){
                while (current.getPoint().equals(end)){
                    while (current != null){
                        path.add(current);
                        current = current.getPrevious();
                    }
                    ArrayList<Space> reversedPath = new ArrayList<>();
                    for (int i = path.size() - 1; i>= 0; i--){
                        reversedPath.add(path.get(i));
                    }
                    path = reversedPath;
                    return path;
                }

                openList.remove(current);
                closedList.add(current);

                Space[] neighbors = board.getNeighbors(current);
                for (Space neighbor : neighbors){

                    

                 if (closedList.contains(neighbor)|| neighbor.getType() == SpaceType.BLOCK){
                    neighbor.setPrevious(current);
                    neighbor.setG(current.getG() + 1);
                    neighbor.setH(manhattanDistance(neighbor.getPoint(), end));
                    if (!openList.contains(neighbor)){
                        openList.add(neighbor);
                    }

                }
            }
            }
        }


        

        return path;
    }
}
