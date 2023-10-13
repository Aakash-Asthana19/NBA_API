
.. project information
.. |title| replace:: Api
.. |slug| replace:: **cs1302-api**
.. |semester| replace:: Spring 2023
.. |version| replace:: v2023.sp
.. |team_size| replace:: 1
.. |banner| image:: https://github.com/cs1302uga/cs1302-api-app/raw/main/resources/readme-banner.png
   :alt: Image from page 400 of "The Palm of Alpha Tau Api" (1880)
.. |compile_points| replace:: 100
.. |style_points_each| replace:: 5
.. |style_points_max| replace:: 20
.. |server| replace:: Odin

.. notices (need to manually update the urls)
.. |team_size_notice| image:: https://img.shields.io/badge/Team%20Size-1-informational
   :alt: Team Size |team_size|
.. |approval_notice| image:: https://img.shields.io/badge/Approved%20for-Spring%202023-magenta
   :alt: Approved for: |version|

CSCI 1302 - |title| |version|
#############################

|approval_notice| |team_size_notice|

|banner|

This document contains the description for the |slug| project assigned to the
students in the |semester| CSCI 1302 classes at the University of Georgia.

**Please read the entirety of this file before beginning your project.**

----

.. contents::

----

Project Description
*******************

Your goal is to implement, from scratch, an application in Java 17 using JavaFX 17
that incorporates a preponderance of the topics introduced in this course in a way that
demonstrates that you have met the learning outcomes related to those topics.

Your application must integrate two or more external RESTful JSON APIs so that your users don't need
to utilize multiple services themselves to get the information or content that
they want. Your app needs to do more than just download and display responses
from the external APIs, it needs to combine the responses in some meaningful
way. Combining responses means that the response from one API should be used
(at least in part) to query the second API. Your application must automate the process of
connecting two different APIs for a single purpose.

- Many services provide **free access** to their RESTful JSON APIs -- a RESTful JSON API is
  one that you can access with an HTTP client (like ``java.net.http.HttpClient``) and parse
  with a JSON library like Gson. For this project, you may only use RESTful JSON APIs and no
  other kinds of APIs.

  You can find hundreds of APIs on the web. As a starting point, you may want to take a look
  at this list: https://github.com/public-apis/public-apis. Please note that this is a public
  resource. Not everything has been tested and not everything is safe for work. Please make sure
  you only use APIs that are safe for work and meet the |uga_ndah|_. You must access the APIs
  directly. You are not allowed to use third-party services such as Rapid API (or similar) for
  access.

- Some of these API services do require you to register with them to gain access to
  an "API key" -- an API key is usually just a special string that is unique to you
  that must be incorporated into how you request the JSON response. For example,
  suppose you have an API key for the dog api stored in ``API_KEY``, then you
  might use the following URL when requesting the JSON for a list of breeds
  (see |the_dog_api_breeds|_):

  .. code::

     "https://api.thedogapi.com/v1/breeds?apikey=" + API_KEY

  .. |the_dog_api_breeds| replace:: ``/breeds``
  .. _the_dog_api_breeds: https://docs.thedogapi.com/api-reference/breeds/breeds-list

- You should read the "|working_with_apis|_" appendix section before you write any code.

You have a lot of flexibility with regard to the functionality and
visuals of your app. So long as your app actually functions and you
meet the other requirements, you are free to make the app look and
feel however you want (keep it appropriate).

Remember, part of software development is being given a goal but not
necessarily being given instruction on all of the details needed to
accomplish that goal. For example, even though working with things
like API keys have not been explicitly covered in class, you are going
to need to look up how to do these things in order to complete this
project.

Learning Outcomes
*****************

Here are some of the learning outcomes for this project:

* Plan, design, implement, test, debug, and deploy a complete object-oriented software solution in Linux/Unix environment (1302-LO1).
* Utilize inheritance and polymorphism in a software project (1302-LO3-LO4).
* Develop a GUI for a software project (1302-LO7).
* Implement exception-handling in a software project (1302-LO8).
* Understand and apply language basics using an OOP language (1302-LO11).

.. |freqs| replace:: Functional Requirements
.. _freqs: #functional-requirements

|freqs|
*******

A functional requirement is *added* to your point total if satisfied.

Primary Functions
   Your app must integrate two or more external RESTful JSON APIs
   based on user input and combine the responses in some meaningful /
   interesting way. Combining responses means that the response from
   one API should be used (at least in part) to query the second
   API. Your application must automate the process of connecting two
   different APIs for a single purpose. Failure to meet this
   requirement will result in a grade of 0 for this category. If you
   have questions about whether or not your idea is sufficient, please
   discuss it with an instructor.

Multiple Uses per Execution
   After the application is started,
   your application should allow the user to query the API(s) an arbitrary number of
   times without requiring them to exit and rerun the application. By arbitrary, we
   mean that there is no limit to how many times the user may do this.

Non-Functional Requirements
***************************

A non-functional requirement is *subtracted* from your point total if
not satisfied. In order to emphasize the importance of these requirements,
non-compliance results in the full point amount being subtracted from your
point total. That is, they are all or nothing.

Development Environment
  This project must *must compile and run*
  correctly on Odin using the specific version of Java that is enabled
  by the **CSCI 1302 shell profile**. For this requirement, the term
  *compile* should be interpreted as *compile with no errors or warnings*.

API Access
  You must directly access any APIs used in your project. You are not allowed to
  use third-party services such as Rapid API (or similar) for access.

User-Friendly Experience
   The windows of your application
   should not exceed a pixel dimension of ``1280`` (width) by ``720`` (height).
   Additionally, except for reasonable delays resulting from X forwarding, your
   application should not hang/freeze or crash during execution.

Local Assets / Resources
   All assets (e.g., images), except
   for assets discovered using an external API, need to be pre-downloaded and
   placed either in the ``resources`` (not ``src/main/resouces``) or a directory
   under ``resources``. **This will help make your app faster.** Here are some
   examples that illustrate the relationship between the path for a resource
   and the ``file:`` URL that you need to use in your code:

   =========================  ================================
   Resource                   URL
   =========================  ================================
   ``resources/icon.png``     ``"file:resources/icon.png"``
   ``resources/foo/img.png``  ``"file:resources/foo/img.png"``
   =========================  ================================

Code Style
   Every ``.java`` file that you include as part of your submission for this project must
   be in valid style as defined in the `CS1302 Code Style Guide <https://github.com/cs1302uga/cs1302-styleguide>`_.
   All of the individual code style guidelines listed in the style guide document are considered for
   this requirement.

   If ``check1302`` on |server| reports any style violations for your submission, then
   |style_points_each| points will be subtracted from your earned point total **for each
   violation**, up to a maximum deduction of |style_points_max| points.

Attribution
   Proper attribution should be given for **all assets**
   (e.g., art, sound, music, etc.) that you include in your project, especially assets
   that you did not personally author. All such attributions needs to be placed in the
   ``meta/ATTRIBUTION.md`` file.

   For each asset that you authored, please provide the following information:

   .. code::

      * Asset Name
        - `resources/path/to/file`
        - Your Name. Year.

   For each asset that you did not personally author, please provide the following
   information:

   .. code::

      * Asset Name
        - `resources/path/to/file`
        - Author. Year.
        - URL
        - License

   :NOTE:
      Don't forget to stage and commit your ``meta/ATTRIBUTION.md`` file after you
      update it!

Final Project Policies
**********************

.. |final_pols| replace:: Final Project Policies
.. _final_pols: https://github.com/cs1302uga/cs1302-api#final-project-policies

No use of ``JsonArray``, ``JsonElement``, ``JsonObject``, and ``JsonParser``
   You may not use or mention the following classes provided by Gson:

   * ``com.google.gson.JsonArray``
   * ``com.google.gson.JsonElement``
   * ``com.google.gson.JsonObject``
   * ``com.google.gson.JsonParser``

   To parse a JSON-formatted string, use a ``Gson`` object's ``fromJson`` method to parse
   the string directly into instances of classes that represent the data. Classes for
   an iTunes Search response and result are provided with the starter code. Instructions
   for parsing JSON-formatted strings using ``fromJson`` is described in the
   `JSON reading <https://github.com/cs1302uga/cs1302-tutorials/blob/master/web/json.rst>`__.

No use of the ``openStream()`` method in ``URL``
  You may not use or mention the ``openStream()`` method provided by the ``java.net.URL`` class.
  If you need to access web content, then use an HTTP client as described in the
  `HTTP reading <https://github.com/cs1302uga/cs1302-tutorials/blob/master/web/http.rst>`__.

Working on a Local Machine
   If you decide to work on part or all of the project on your local machine,
   then it's your responsibility to ensure that your environment is compatible
   with the versions of software on Odin. No technical assistance will be provided
   by the instructional staff to accommodate this beyond the information provided
   in this policy statement. Remember, **your code still needs to compile and
   run on Odin** per the "Development Environment" absolute requirement. That is,
   if your submission does not compile on Odin, then that will result in an
   immediate zero for the assignment. A list of the relevant software versions
   currently in use on Odin (at the time of this writing) is provided below for
   convenience.

   * **Apache Maven 3.8.6**
        https://maven.apache.org/
   * **Java 17.0.5** (vendor: Oracle Corporation; **not OpenJDK**)
        https://www.oracle.com/java/technologies/downloads/

   All other dependencies are handled via Maven.

Appendix
********

.. rubric:: **JavaFX**

* `JavaFX 17 API Documentation <https://openjfx.io/javadoc/17/>`__
* `CSCI 1302 JavaFX Bookmarks <https://github.com/cs1302uga/cs1302-tutorials/blob/master/javafx/javafx-bookmarks.md>`__
* `CSCI 1302 JavaFX Tutorial <https://github.com/cs1302uga/cs1302-tutorials/blob/master/javafx/javafx.md>`__

.. rubric:: **Git**

.. |git_feature_workflow| replace:: Git Feature Branch Workflow
.. _git_feature_workflow: https://github.com/cs1302uga/cs1302-api-app/blob/main/APPENDIX_GIT.rst

* |git_feature_workflow|_

.. rubric:: **RESTful JSON APIs**

.. |working_with_apis| replace:: Working with RESTful JSON APIs
.. _working_with_apis: https://github.com/cs1302uga/cs1302-api-app/blob/main/APPENDIX_API.rst

* |working_with_apis|_

FAQ
***

.. |cs1302_gallery| replace:: cs1302-gallery FAQ
.. _cs1302_gallery: https://github.com/cs1302uga/cs1302-gallery#appendix---faq

Below are some frequently asked questions related to this project.
You may also find the |cs1302_gallery|_ a useful resource as well.

1. **May I use an API not mentioned in the project description?**

   .. rubric:: **RESTful JSON API**

   If you're asking about a RESTful JSON API that's not mentioned in the project
   description, then probably yes! Here are the requirements:

   * the API must provide a JSON response based on a request to a URL that
     is pragmatically generated by your program.

   If you're not sure about an API, then ask on Piazza.

   .. rubric:: **Java API**

   If you're asking about a third-party Java API that is not included with
   Java 17, JavaFX 17.0.2, Gson 2.9.0, or the starter code, then the answer is no.

2. **How do I add sound?**

   While JavaFX does support audio playback of various formats, this feature is not
   currently available over X11 forwarding from Odin. We're sorry to say this, but
   **you should not attempt to add audio to your application** for this project.

3. **How can I generate my Javadoc using Maven?**

   For this project, a ``site.sh`` script is provided that will deploy
   a Maven site, including Javadoc, to your ``~/public_html/cs1302-api``
   directory when executed on Odin. The script will display the URL of the
   deployed site near the end of its execution. Here is the command::

     $ ./site.sh

.. #############################################################################

.. readings
.. |reading_github_setup| replace:: Setting up your own GitHub Account
.. _reading_github_setup: https://github.com/cs1302uga/cs1302-tutorials/blob/master/github-setup.md

.. instructor github profiles
.. |mepcotterell| replace:: ``mepcotterell``
.. _mepcotterell: https://github.com/mepcotterell
.. |bjb211| replace:: ``bjb211``
.. _bjb211: https://github.com/bjb211

.. util
.. |Y| unicode:: U+2713
.. |N| unicode:: U+2717

.. copyright and license information
.. |copy| unicode:: U+000A9 .. COPYRIGHT SIGN
.. |copyright| replace:: Copyright |copy| Michael E. Cotterell, Bradley J. Barnes, and the University of Georgia.
.. |license| replace:: CC BY-NC-ND 4.0
.. _license: http://creativecommons.org/licenses/by-nc-nd/4.0/
.. |license_image| image:: https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg
                   :target: http://creativecommons.org/licenses/by-nc-nd/4.0/
.. standard footer
.. footer:: |license_image|

   |copyright| This work is licensed under a |license|_ license to students
   and the public. The content and opinions expressed on this Web page do not necessarily
   reflect the views of nor are they endorsed by the University of Georgia or the University
   System of Georgia.

----

.. rubric:: **Feature Preparation Timestamps:**
