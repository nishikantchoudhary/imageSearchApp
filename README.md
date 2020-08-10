# imageSearchApp

This is an Image searching app, which searches images and user can add comments to it that will be strored locally.

The project consists of following major class and their behaviours are detailed:

ImageRepository: A repository deals in searching images, storing comments, fetching comments for the corresponding images.

ImageViewModel, CommentViewModel: An data oberserver which view can observe to get data. If the remote api call failes then it delivers the updated network error to the view.
