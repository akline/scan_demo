# Scan Demo

Quick scan demo based on tensorflow image retraining script and android classifier app. 

### Training 

In the training directory, 

```
python training/retrain.py --how_many_training_steps 4000 --image_dir training/images
```

Default training steps is 4000, image dir can be anywhere, subdirectories of the image dir become labels. 

Resulting graph and labels are `/tmp/output_graph.pb` and `tmp/output_labels.txt`. 

### Graph Prep 

Prep the graph for mobile 

```
python optimize_for_inference.py --input=/tmp/output_graph.pb --output=/tmp/output_graph_optimized.pb --input_names=Mul --output_names=final_result
```

### App 

Put the new graph and labels in the app's asset dir and build. 

```
cp /tmp/output_graph_optimized.pb app/android/assets
cp /tmp/output_labels.txt app/android/assets
```

The app/android directory should work as an android studio project with no tensorflow codebase or bazel dependencies. 
