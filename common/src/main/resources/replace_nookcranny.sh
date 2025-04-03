#!/bin/bash

# Find all .json files in the current directory and subdirectories
find . -type f -name "*.json" | while read -r file; do
    # Replace "nookcranny" with "omf" in each file
    sed -i 's/nookcranny/omf/g' "$file"
    echo "Updated: $file"
done

echo "Replacement complete!"
