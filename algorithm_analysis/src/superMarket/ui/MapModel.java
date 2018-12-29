package ui;
/**
 * @author Sun
 */
import java.util.ArrayList;
import arithmetic.*;

public class MapModel {

	public String[] name;
	public float[] freq;
	public float length[][];
	public Map map;
	public Arithmetic ari;
	public ArrayList<int[]> path = new ArrayList<int[]>();
	public ArrayList<Float> minLength = new ArrayList<Float>();

	public void count() {

		buildMap();
		ari = new Arithmetic(map);
		for (int i = 0; i < map.vertex.size(); i++) {
			for (int j = 0; j < map.vertex.size(); j++) {
				if (i != j) {
					path.add(ari.getOptimalPath(j, i));
					minLength.add(ari.getLength());
					ari.setLength(0);
				}
			}
		}
	}

	public void buildMap() {

		map = new Map(name, freq, length);
	}

	public void getData(ArrayList<RoomPane> list) {

		name = new String[list.size()];
		freq = new float[list.size()];
		for (int i = 0; i < list.size(); i++) {
			name[i] = list.get(i).apane.name;
			freq[i] = list.get(i).apane.freq;
		}
		length = new float[list.size()][list.size()];
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				if (list.get(i).apane.model.contains(name[j])) {
					int index = list.get(i).apane.model.indexOf(name[j]);
					length[i][j] = (Float) list.get(i).apane.lenmodel
							.getElementAt(index);
				} else {
					length[i][j] = -1;
				}
			}
		}
	}

	public ArrayList<int[]> getPath() {
		return path;
	}

	public ArrayList<Float> getMinLength() {
		return minLength;
	}

}
