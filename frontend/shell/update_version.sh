CURRENT_VERSION=$(cat index.html | grep "version=" | head -n 1 | awk -F"version=" '{print $2}' |  awk -F"\"" '{print $1}')
NEW_VERSION=`expr $CURRENT_VERSION + 1`

sed -i "s/?version=$CURRENT_VERSION/?version=$NEW_VERSION/" index.html
cat index.html | grep version
