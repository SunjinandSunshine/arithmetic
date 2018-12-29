package arithmetic;
/**
 * @author Sun
 */
import java.util.*;
public class Map {

	public ArrayList<Vertex> vertex = new ArrayList<Vertex>();
	public ArrayList<List<Edge>> edge = new ArrayList<List<Edge>>();
	public Map() {
	}
	public Map(String vn[], float vf[], float list[][]) {
		build(vn, vf, list);
	}
	//构造边
	private void build(String vn[], float vf[], float list[][]) {
		ArrayList<Edge> edgeList;
		for (int i = 0; i < vn.length; i++) {
			vertex.add(new Vertex(vn[i], vf[i]));
			edgeList = new ArrayList<Edge>();
			for (int j = 0; j < list[0].length; j++) {
				if (list[i][j] > 0) {
					edgeList.add(new Edge(i, j, list[i][j]));
				}
			}
			edge.add(edgeList);
		}
	}
    //根据名字查找顶点
	public int getIndex(String name) {
		for (int i = 0; i < this.vertex.size(); i++) {
			if (name.equals(this.vertex.get(i).getName())) {
				return i;
			}
		}
		return -1;
	}

	public ArrayList<Vertex> getVertex() {
		return vertex;
	}

	public void setVertex(ArrayList<Vertex> vertex) {
		this.vertex = vertex;
	}

	public ArrayList<List<Edge>> getEdge() {
		return edge;
	}

	public void setEdge(ArrayList<List<Edge>> edge) {
		this.edge = edge;
	}
}

class Vertex {
    //顶点：名字和频繁度
	public String name;
	public float frequency;

	public Vertex() {
	}
	public Vertex(String name, float f) {

		this.name = name;
		this.frequency = f;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public float getFrequency() {
		return frequency;
	}

	public void setFrequency(float frequency) {
		this.frequency = frequency;
	}

}

class Edge {

	public int own;
	public int arrive;
	public float length;//距离

	public Edge() {
	}

	public Edge(int a, int b, float len) {

		this.own = a;
		this.arrive = b;
		this.length = len;
	}

	public int getOwn() {
		return own;
	}

	public void setOwn(int own) {
		this.own = own;
	}

	public int getArrive() {
		return arrive;
	}

	public void setArrive(int arrive) {
		this.arrive = arrive;
	}

	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
	}

}
