2D_Optimization
===============

A small software that generates a sequence of movements to go from a 2D configuration to another (with stacking restriction)<br>
It includes a easy-to-use GUI in swing.<br>
Example of problem :<br>
<br>
e means empty<br>
<br>
Initial configuration :<br>
e e e e<br>
1 2 e e<br>
3 4 5 e<br>
<br>
Final configuration :<br>
e e e e<br>
4 3 e e<br>
5 1 2 e<br>
<br>
Solution returned :<br>
<br>
  Deplacement du Bloc5 en (0,2)<br>
  Deplacement du Bloc2 en (2,0)<br>
  Deplacement du Bloc5 en (1,1)<br>
  Deplacement du Bloc1 en (2,1)<br>
  Deplacement du Bloc3 en (2,2)<br>
  Deplacement du Bloc5 en (0,0)<br>
  Deplacement du Bloc4 en (0,1)<br>
  Deplacement du Bloc3 en (0,2)<br>
  Deplacement du Bloc1 en (1,0)<br>
  Deplacement du Bloc3 en (1,1)<br>
<br>
The hub contains a full, ready-to-import eclipse project<br>
<br>
Everything is in French (code, GUI, comments, doc)
