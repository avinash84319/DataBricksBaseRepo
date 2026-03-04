from setuptools import setup, find_packages

setup(
    name="project1Python",
    version="0.1.0",
    packages=find_packages(),
    install_requires=[
    ],
    package_data={
        "": ["*.txt", "*.md"],
    },
    entry_points={
        "console_scripts": [
            "run_project1=project1Python.main:main",
        ],
    },
)