import java.util.ArrayList;
import java.util.Iterator;

public class  FileStructure {
    private NLNode<FileObject> root;

    //  this is the constructor which is used to make the root node of the entire tree
    public FileStructure(String fileObjectName) throws FileObjectException {
        FileObject rootFileObject = new FileObject(fileObjectName);

        root = new NLNode<FileObject>(rootFileObject, null);
        create(root);
    }

    private void create(NLNode<FileObject> r) {
//        base case indicated for when the node is a file
        if (r.getData().isFile()) {
            return;
        }
//        otherwise it is a directory file and we make the recursive case.
        Iterator<FileObject> f = r.getData().directoryFiles();

        while (f.hasNext()) {
            NLNode<FileObject> n = new NLNode<FileObject>(f.next(), null);
            n.setParent(r);
            r.addChild(n);
            create(n);
        }
    }

//        getter for the root node

    public NLNode<FileObject> getRoot() {
        return root;
    }


    //    this method returns a selection of files based on its type of file
    public Iterator<String> filesOfType(String type) {
        ArrayList<String> list1 = new ArrayList<String>();
        typeOfFileSearch(root, type, list1);
        return list1.iterator();
    }

    private void typeOfFileSearch(NLNode<FileObject> r, String fileType, ArrayList<String> list1) {

//        this holds the base case for the NLNode when r is a file and if this file is within the fileType we are
//        searching for then we add it our ArrayList<String> list1
        if (r.getData().isFile()) {
            if (r.getData().getLongName().endsWith(fileType)) {
                list1.add(r.getData().getLongName());
            }
        } else {
//            recursive case for when it is not a file but instead a folder
            Iterator<FileObject> f = r.getData().directoryFiles();
            while (f.hasNext()) {
                NLNode<FileObject> n = new NLNode<FileObject>(f.next(), null);
                typeOfFileSearch(n, fileType, list1);
            }
        }
    }

    // this method is used to check the file to see its name and then return this file
    private String findFileSearch(NLNode<FileObject> r1, String fileSearch) {
// base case for if the file we are looking for is a file and equals the parameter fileSearch (named importantFile)
        if (r1.getData().isFile()) {
            if (r1.getData().getName().equals(fileSearch)) {
                String importantFile = r1.getData().getLongName();
                return importantFile;
            }
        }else {
// recursive case, we use directory file to access children. we use f.hasNext()
            Iterator<FileObject> f = r1.getData().directoryFiles();
            while (f.hasNext()) {
                NLNode<FileObject> n = new NLNode<FileObject>(f.next(), null);
                String importantFile2 = findFileSearch(n, fileSearch);
                if (!importantFile2.equals("")) {
                    return importantFile2;
                }

            }
//                return empty string if not found
            return "";

        }
        return "";
    }


        public String findFile(String name){
            String name1 = findFileSearch(root, name);
//      Check if null or empty if so return empty string
            if(name == null || name1.isEmpty()) {
                return "";
            }else if (name1 != null){
//      when not null we return the name of the file in our private helper method which we call here.
                return name1;
        }
            return name1;
    }

}




