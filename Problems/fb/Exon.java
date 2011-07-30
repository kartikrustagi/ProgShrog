class Exon implements Comparable<Exon> {
  int start, end, weight, overlapsTo, score;

  public Exon(int start) {
    this.start = start;
    this.score = 0;
  }

  public int compareTo (Exon e) {
    if (this == e) return 0;
    if (this.start < e.start) return -1;
    if (this.start > e.start) return 1;
    return 0;
  }
}